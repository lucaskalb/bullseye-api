require 'rails_helper'

RSpec.describe 'Payments API', type: :request do
  let(:user) { create(:user) }
  let(:category) { create(:category) }

  let!(:statement) { create( :statement, user_id: user.id, category_id: category.id ) }
  let(:statement_id) { statement.id }

  let!(:statement_to_pay) { create( :statement, user_id: user.id, category_id: category.id ) }
  let(:statement_to_pay_id) { statement_to_pay.id }

  let!(:statement_without_payment) { create( :statement, user_id: user.id, category_id: category.id ) }
  let(:statement_without_payment_id) { statement_without_payment.id }

  let!(:payment) { create(:payment, statement_id: statement_id) }

  let(:headers) { valid_headers }

  describe 'GET /payments' do
    before { get "/payments", params: {}, headers: headers }

    it 'returns payments' do
      expect(json).not_to be_empty
      expect(json.size).to eq(1)
    end

    it 'returns status code 200' do
      expect(response).to have_http_status(200)
    end
  end

  describe 'GET /statements/:statement_id/payment' do
    before { get "/statements/#{statement_id}/payment", params: {}, headers: headers }

    context 'when the record exists' do
      it 'returns the statement' do
        expect(json).not_to be_empty
        expect(json['id']).to eq(payment.id)
      end

      it 'returns status code 200' do
        expect(response).to have_http_status(200)
      end
    end

    context 'when the record does not exist' do
      before { get "/statements/#{statement_without_payment_id}/payment", params: {}, headers: headers }
      it 'returns status code 404' do
        expect(response).to have_http_status(404)
      end

      it 'returns a not found message' do
        expect(response.body).to match(/Couldn't find Payment/)
      end
    end
  end

  describe 'POST /statements/:statement_id/payment' do
    let(:valid_attributes) {
      {
        date: Date.today,
        comment: 'Paid with my money' ,
        value: 10.00
      }.to_json
    }

    context 'when the request is valid' do
      before { post "/statements/#{statement_to_pay_id}/payment", params: valid_attributes, headers: headers }

      it 'creates a statement' do
        expect( json['date'] ).to     eq( Date.today.to_s )
        expect( json['value'] ).to    eq( "10.0" )
        expect( json['comment'] ).to  eq( 'Paid with my money' )
      end

      it 'returns status code 201' do
        expect(response).to have_http_status(201)
      end

      it 'should have changed the status of the statement for payment' do
        statement = Statement.find( statement_id )
        expect( statement.payment ).not_to be_nil
        expect( statement.paid? ).to be_truthy
      end
    end


    context 'when the request is invalid' do
      let(:invalid_attributes) { { date: nil, value: nil }.to_json }

      before { post "/statements/#{statement_id}/payment", params: invalid_attributes, headers: headers }

      it 'returns status code 422' do
        expect(response).to have_http_status(422)
      end

      it 'returns a validation failure message' do
        expect(response.body).to match(/Date can't be blank/)
        expect(response.body).to match(/Value can't be blank/)
      end
    end

    context 'when the request is invalid because statement already a payment' do
      before { post "/statements/#{statement_id}/payment", params: valid_attributes, headers: headers }

      it 'returns status code 422' do
        expect(response).to have_http_status(422)
      end

      it 'returns a validation failure message' do
        expect(response.body).to match(/Statement has already been taken/ )
      end
    end
  end

  describe 'PUT /statement/:statement_id/payment' do
    let(:valid_attributes) { { value: 20.00 }.to_json }

    context 'when the record exists' do
      before { put "/statements/#{statement_id}/payment", params: valid_attributes,  headers: headers }

      it 'updates the record' do
        expect( response.body ).to be_empty

        statement = Statement.find( statement_id )
        expect( statement.payment ).not_to be_nil
        expect( statement.payment.value.to_s ).to eq( 20.00.to_s )
      end

      it 'returns status code 204' do
        expect( response ).to have_http_status( 204 )
      end

      context 'when the record does not exist' do
        before { put "/statements/#{statement_without_payment_id}/payment", params: {}, headers: headers }
        it 'returns status code 404' do
          expect(response).to have_http_status(404)
        end

        it 'returns a not found message' do
          expect(response.body).to match(/Couldn't find Payment/)
        end
      end
    end
  end

  describe 'DELETE /statements/:statement_id/payment' do
    before { delete "/statements/#{statement_id}/payment", params: {}, headers: headers }

    it 'returns status code 204' do
      expect(response).to have_http_status(204)
    end

    it 'remove a payment and change statement status to opened' do
      statement = Statement.find( statement_id )
      expect( statement.payment ).to be_nil
      expect( statement.opened? ).to be_truthy
    end
  end
end
