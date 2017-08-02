class Payment < ApplicationRecord
  belongs_to :statement

  validates_presence_of   :statement, :date, :value
  validates_uniqueness_of :statement

  after_create  :update_statement_to_paid
  after_destroy :update_statement_to_opened


  private

  def update_statement_to_paid
    self.statement.paid!
    self.statement.save!
  end

  def update_statement_to_opened
    self.statement.opened!
    self.statement.save!
  end
end
