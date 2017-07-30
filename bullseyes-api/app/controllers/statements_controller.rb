class StatementsController < ApplicationController
  before_action :set_statement, only: [ :show, :update, :destroy ]
  before_action :set_by_statement_id, only: [ :cancel ]

  # GET /statements
  def index
    json_response(Statement.all)
  end

  # POST /statements
  def create
    @parameters = statement_params
    @parameters[:user_id] = current_user.id
    json_response( Statement.create!( @parameters ), :created )
  end

  # GET /statements/:id
  def show
    json_response(@statement)
  end

  # PUT /statements/:id
  def update
    @parameters = statement_params
    @statement.update(@parameters)
    head :no_content
  end

  # PUT /statements/:id/cancel
  def cancel
    @statement.canceled!
    @statement.save!
    head :no_content
  end

  # DELETE /statements/:id
  def destroy
    @statement.destroy
    head :no_content
  end

  private

  def statement_params
    params.permit( :title, :due_date, :observation, :expected_value, :category_id )
  end

  def set_statement
    @statement = Statement.where( id: params[:id], user_id: current_user.id ).take!
  end

  def set_by_statement_id
    @statement = Statement.where( id: params[:statement_id], user_id: current_user.id ).take!
  end

end
