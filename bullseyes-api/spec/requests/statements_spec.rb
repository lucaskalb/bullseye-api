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
end