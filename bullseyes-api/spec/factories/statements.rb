FactoryGirl.define do
  factory :statement do
    title { Faker::StarWars.character }
    status :opened
    due_date Date.today
    expected_value { Faker::Number.decimal(2) }
  end
end