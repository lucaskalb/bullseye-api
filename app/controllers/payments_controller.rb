class PaymentsController < ApplicationController
  before_action :set_statement, only: [ :show, :update, :destroy, :create ]
  before_action :set_payment, only:   [ :show, :update, :destroy ]


  def index
    json_response( Payment.all )
  end

  def show
    json_response( @payment )
  end

  def create
    json_response( Payment.create!( payment_params ), :created )
  end

  def update
    @payment.update(payment_params)
    head :no_content
  end

  def destroy
    @payment.destroy
    head :no_content
  end


  private

  def payment_params
    params.permit( :statement_id, :date, :value, :comment )
  end


  def set_statement
    @statement = Statement.where( id: params[:statement_id], user_id: current_user.id ).take!
  end

  def set_payment
    @payment = Payment.where( statement_id: params[:statement_id]  ).take!
  end


end
