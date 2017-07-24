FactoryGirl.define do
  factory :statement do
    title    { Faker::Lorem.word }
    category { Faker::Lorem.word }
    status   { Faker::Lorem.word }
    due_date { Faker::Date.backward(14) }
  end
end