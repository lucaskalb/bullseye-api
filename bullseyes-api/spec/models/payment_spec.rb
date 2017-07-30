require 'rails_helper'

RSpec.describe Payment, type: :model do
  it { should validate_presence_of(:date) }
  it { should validate_presence_of(:statement) }
  it { should validate_presence_of(:value) }
end
