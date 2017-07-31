class Nubank
  require 'csv'

  attr_accessor :date, :category, :title, :amount, :paid, :payment_date

  def initialize args
    args.each do |k,v|
      instance_variable_set("@#{k}", v) unless v.nil?
    end
  end

  def self.extract file
    datas = []

    CSV.foreach( file.path, headers: true) do |row|
      datas << Nubank.new(row.to_hash)
    end
    datas
  end

  def save user
    _statement = Statement.new
    _statement.title = self.title
    _statement.due_date =  Date.parse self.date
    _statement.observation = 'credit card - nubank'
    _statement.expected_value = self.amount.to_f
    _statement.category = Category.create_if_non_exists self.category
    _statement.user = user
    _statement.save!

    if self.paid
      _payment = Payment.new
      _payment.date = self.payment_date == nil ? Date.today : payment_date
      _payment.value = self.amount.to_f
      _payment.statement = _statement
      _payment.save!

      _statement.payment = _payment
    end

    _statement
  end
end
