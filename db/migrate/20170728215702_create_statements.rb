class CreateStatements < ActiveRecord::Migration[5.1]
  def change
    create_table :statements do |t|
      t.string :title
      t.integer :status, default: 0
      t.date :due_date
      t.text :observation
      t.decimal :expected_value
      t.references :category, foreign_key: true
      t.references :user, foreign_key: true

      t.timestamps
    end
  end
end
