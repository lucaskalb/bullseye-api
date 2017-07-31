Rails.application.routes.draw do
  post 'auth/login', to: 'authentication#authenticate'
  post 'signup', to: 'users#create'

  resources :categories
  resources :statements do
    put    'cancel' , to: 'statements#cancel'

    get    'payment', to: 'payments#show'
    post   'payment', to: 'payments#create'
    put    'payment', to: 'payments#update'
    delete 'payment', to: 'payments#destroy'
  end

  post 'nubank/extract',   to: 'nubank#extract'
  post 'nubank/create',    to: 'nubank#create'

  get  'payments', to: 'payments#index'
end
