FactoryGirl.define do
  factory :category do
    title { Faker::StarWars.character }
  end
end