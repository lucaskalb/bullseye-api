class CreatePayments < ActiveRecord::Migration[5.1]
  def change
    create_table :payments do |t|
      t.date :date
      t.decimal :value
      t.text :comment
      t.references :statement, foreign_key: true

      t.timestamps
    end
  end
end
