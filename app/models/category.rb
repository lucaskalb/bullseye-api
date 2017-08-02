class Category < ApplicationRecord

  validates_presence_of :title
  validates_uniqueness_of :title


  def self.create_if_non_exists title
    _category = Category.where( {title: title} )
    unless  _category.nil?
      _category = Category.create!( {title: title} )
    end
    _category
  end
end
