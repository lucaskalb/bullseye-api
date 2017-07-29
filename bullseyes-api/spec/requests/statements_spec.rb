require 'rails_helper'

RSpec.describe 'Categories API', type: :request do
  let(:user) { create(:user) }
  let(:category) { create(:category) }
  let!(:statements) { create_list( :statement, 10, user_id: user.id, category_id: category.id ) }
  let!(:statement_id) { statements.first.id }
  let(:headers) { valid_headers }

  describe 'GET /statements' do
    before { get '/statements', params: {}, headers: headers }

    it 'returns statements' do
      expect(json).not_to be_empty
      expect(json.size).to eq(10)
    end

    it 'returns status code 200' do
      expect(response).to have_http_status(200)
    end
  end

  describe 'GET /statements/:id' do
    before { get "/statements/#{statement_id}" , params: {}, headers: headers }

    context 'when the record exists' do
      it 'returns the statement' do
        expect(json).not_to be_empty
        expect(json['id']).to eq(statement_id)
      end

      it 'returns status code 200' do
        expect(response).to have_http_status(200)
      end
    end

    context 'when the record does not exist' do
      let(:statement_id) { 100 }

      it 'returns status code 404' do
        expect(response).to have_http_status(404)
      end

      it 'returns a not found message' do
        expect(response.body).to match(/Couldn't find Statement/)
      end
    end
  end

  describe 'POST /statements' do
    let(:valid_attributes) { 
      { 
        title: 'Restaurant',
        due_date: Date.today,
        observation: 'It was a lunch' ,
        expected_value: 10.00,
        category_id: category.id
      }.to_json 
    }

    context 'when the request is valid' do
      before { post '/statements', params: valid_attributes, headers: headers }

      it 'creates a statement' do
        expect( json['title'] ).to          eq( 'Restaurant' )
        expect( json['due_date'] ).to       eq( Date.today.to_s )
        expect( json['observation'] ).to    eq( 'It was a lunch' )
        expect( json['expected_value'] ).to eq( "10.0" )
        expect( json['status'] ).to         eq( "opened" )
        expect( json['category_id'] ).to    eq( category.id )
      end

      it 'returns status code 201' do
        expect(response).to have_http_status(201)
      end
    end

    context 'when the request is invalid' do
      let(:invalid_attributes) { 
        { 
          title: nil,
          due_date: nil,
        }.to_json 
      }
      before { post '/statements', params: invalid_attributes, headers: headers }

      it 'returns status code 422' do
        expect(response).to have_http_status(422)
      end

      it 'returns a validation failure message' do
        expect(response.body)
          .to match(/Title can't be blank/)
        expect(response.body)
          .to match(/Category can't be blank/)
      end
    end
  end

  describe 'PUT /statement/:id' do
    let(:valid_attributes) { { title: 'Bar' }.to_json }

    context 'when the record exists' do
      before { put "/statements/#{statement_id}", params: valid_attributes,  headers: headers }

      it 'updates the record' do
        expect(response.body).to be_empty

        statement = Statement.all.first
        expect( statement.title ).to eq( "Bar" )
      end

      it 'returns status code 204' do
        expect( response ).to have_http_status( 204 )
      end
    end
  end

  describe 'PUT /statements/cancel' do
    before { put "/statements/#{statement_id}/cancel", params: {}, headers: headers }

    it 'returns status code 204' do
      expect(response).to have_http_status(204)
      
      statement = Statement.all.first
      expect( statement.status ).to eq( "canceled" )
    end
  end

  describe 'DELETE /statements/:id' do
    before { delete "/statements/#{statement_id}", params: {}, headers: headers }

    it 'returns status code 204' do
      expect(response).to have_http_status(204)
    end
  end

end