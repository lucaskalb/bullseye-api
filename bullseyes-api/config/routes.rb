Rails.application.routes.draw do
  resources :categories
  resources :statements
  put 'statements/:id/cancel', to: 'statements#cancel'
  post 'auth/login', to: 'authentication#authenticate'
  post 'signup', to: 'users#create'
end