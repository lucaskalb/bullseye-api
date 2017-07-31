class NubankController < ApplicationController

  # POST /nubank/extract
  def extract
    json_response( Nubank.extract( params[:file] ) )
  end

  # POST /nubank/create
  def create
    @nubank = Nubank.new(params)
    json_response( @nubank.save( current_user ), :created )
  end

end
