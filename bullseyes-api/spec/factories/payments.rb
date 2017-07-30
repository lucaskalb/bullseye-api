FactoryGirl.define do
  factory :payment do
    date Date.today
    value { Faker::Number.decimal(2) }
    comment { Faker::Lorem.paragraph }
  end
end
