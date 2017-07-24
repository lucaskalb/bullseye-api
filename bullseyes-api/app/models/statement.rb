class Statement 
    include Mongoid::Document
    include Mongoid::Timestamps::Created
    include Mongoid::Timestamps::Updated

    field :title, type: String
    field :category, type: String
    field :status, type: String
    field :due_date, type: Date
    field :expected_value, type: BigDecimal
    field :observation, type: String

    validates_presence_of :title, :category, :status, :due_date
end