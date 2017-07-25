class StatementsController < ApplicationController

  before_action :set_statement, only: [:title, :category, :status, :due_date]
  before_action :set_statement, only: [:id]

  # GET /statements
  def index
    @statements = Statement.all
    json_response(@statements)
  end

  # POST /todos
  def create
    @statement = Statement.create!(statement_params)
    json_response(@statement, :created)
  end

  # GET /statements/:id
  def show
    json_response( Statement.find( params[:id] ) )
  end

  # PUT /statements/:id
  def update
    @statement.update(statement_params)
    head :no_content
  end

  # DELETE /statements/:id
  def destroy
    @statement.destroy
    head :no_content
  end

  private

  def statement_params
    params.permit(:title, :category, :status, :due_date)
  end

end
