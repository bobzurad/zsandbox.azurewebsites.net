defmodule WebappWeb.PageController do
  use WebappWeb, :controller

  def home(conn, _params) do
    render(conn, :home)
  end
end
