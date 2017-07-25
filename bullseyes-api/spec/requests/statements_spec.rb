require 'rails_helper'

RSpec.describe 'Statement API', type: :request do
  let!(:statements) { create_list(:statement, 10) }
  let(:statement_id) { statements.first.id }

  describe 'GET /statements' do
    before { get '/statements' }

    it 'returns statements' do
      expect(json).not_to be_empty
      #expect(json.size).to eq(10)
    end

    it 'returns status code 200' do
      expect(response).to have_http_status(200)
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
  end
end