class StatementsController < ApplicationController

  before_action :set_statement, only: [:title, :category, :status, :due_date]

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
    json_response(@statement)
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
    # whitelist params
    params.permit(:title, :created_by)
  end

  def set_statement
    @statement = Statement.find(params[:id])
  end
end
