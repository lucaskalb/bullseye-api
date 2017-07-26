require 'rails_helper'

RSpec.describe 'Statement API', type: :request do
  let!(:statements) { create_list(:statement, 10) }
  let(:statement_id) { statements.first.id }

  describe 'GET /statements' do
    before { get '/statements' }

    it 'returns statements' do
      expect(json).not_to be_empty
      expect(json.size).to eq(10)
    end

    it 'returns status code 200' do
      expect(response).to have_http_status(200)
    end
  end

  describe 'GET /statements/:id' do
    before { get "/statements/#{statement_id}" }

    context 'when the record exists' do
      it 'returns the statement' do
        expect(json).not_to be_empty
        expect(json['_id']).to eq(statement_id.to_s)
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
        expect(response.body).to match(/Record not found/)
      end
    end
  end

  describe 'POST /statements' do
    let(:valid_attr) { {title: 'house rent', category: 'home', status: 'OPEN', due_date: '2017-09-01'} }

    context  'when the request is valid' do
      before { post '/statements', params: valid_attr }

      it 'returns statements' do
        expect(json['title']).to eq('house rent')
        expect(json['category']).to eq('home')
        expect(json['status']).to eq('OPEN')
      end

      it 'returns status code 201' do
        expect(response).to have_http_status(201)
      end
    end
    
    context 'when the request is invalid' do
      before { post '/statements', params: { title: 'Blah' } }

      it 'returns status code 422' do
        expect(response).to have_http_status(422)
      end

      it 'returns a validation failure message' do
        expect(response.body).to match(/Validation failed: Created by can't be blank/)
      end
    end
  end
end