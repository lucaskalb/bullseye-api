require 'rails_helper'

include ActionDispatch::TestProcess


RSpec.describe 'Nubank API', type: :request do
  let(:user) { create(:user) }
  let(:headers) { valid_headers }
  let(:file) { fixture_file_upload('files/nubank.csv') }

  describe 'POST /nubank/extract' do
    before { post '/nubank/extract', params: { file: file }, headers: headers }

    it 'returns categories' do
      expect(json).not_to be_empty
      expect(json.size).to eq(38)
    end

    it 'returns status code 200' do
      expect(response).to have_http_status(200)
    end
  end

  describe 'POST /nubank/create' do
    let(:valid_attributes) {
      {
        date: "2017-06-18",
        category: "Transporte",
        title: "Gasolina",
        amount: "73.75",
        paid: true
      }.to_json
    }

    let(:no_payment_request) {
      {
        date: "2017-06-18",
        category: "Casa",
        title: "Luz",
        amount: "30.55",
      }.to_json
    }

    context 'when the request is valid' do
      before { post '/nubank/create', params: valid_attributes, headers: headers }

      it 'creates a statement' do
        expect( json['title'] ).to          eq( 'Gasolina' )
        expect( json['due_date'] ).to       eq( '2017-06-18' )
        expect( json['expected_value'] ).to eq( '73.75' )
        expect( json['status'] ).to         eq( 'paid' )
        expect( json['category_id'] ).to    eq( Category.where( {title: 'Transporte'} )[0].id )
      end

      it 'returns status code 201' do
        expect(response).to have_http_status(201)
      end

      it 'created a Payment' do
        payment = Payment.where( {statement_id: json['id'].to_i} )[0]
        expect( payment ).not_to be_nil
        expect( payment.value ).to eq( 73.75 )
        expect( payment.date ).to eq( Date.today )
      end
    end

    context 'when the request is no payment' do
      before { post '/nubank/create', params: no_payment_request, headers: headers }

      it 'creates a statement' do
        expect( json['title'] ).to          eq( 'Luz' )
        expect( json['due_date'] ).to       eq( '2017-06-18' )
        expect( json['expected_value'] ).to eq( '30.55' )
        expect( json['status'] ).to         eq( 'opened' )
        expect( json['category_id'] ).to    eq( Category.where( {title: 'Casa'} )[0].id )
      end

      it 'returns status code 201' do
        expect(response).to have_http_status(201)
      end

      it ' not created a Payment' do
        payment = Payment.where( {statement_id: json['id'].to_i} )[0]
        expect( payment ).to be_nil
      end
    end
  end
end
