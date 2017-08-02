class Statement < ApplicationRecord

  enum status: [ :opened, :paid, :canceled ]

  belongs_to :category
  belongs_to :user
  has_one :payment

  validates_presence_of :title, :status, :category, :user

  before_create :opening

  private

  def opening
    self.status = "opened"
  end
end
