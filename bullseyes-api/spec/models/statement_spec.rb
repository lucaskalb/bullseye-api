require 'rails_helper'

RSpec.describe Statement, type: :model do
  it { should validate_presence_of( :title    ) }
  it { should validate_presence_of( :status   ) }
  it { should validate_presence_of( :category ) }
  it { should validate_presence_of( :user     ) }
end
