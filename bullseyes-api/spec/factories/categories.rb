FactoryGirl.define do
  factory :category do
    title { Faker::Name.first_name }
  end
end