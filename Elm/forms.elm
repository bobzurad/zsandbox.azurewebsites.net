import Html exposing (..)
import Html.App as Html
import Html.Attributes exposing (..)
import Html.Events exposing (onInput)
import String exposing (..)



main =
  Html.beginnerProgram {
    model = model,
    view = view,
    update = update
  }



-- MODEL


type alias Model =
  { name : String
  , password : String
  , passwordAgain : String
  , age : String
  }


model : Model
model =
  Model "" "" "" ""



-- UPDATE


type Msg
    = Name String
    | Password String
    | PasswordAgain String
    | Age String


update : Msg -> Model -> Model
update action model =
  case action of
    Name name ->
      { model | name = name }

    Password password ->
      { model | password = password }

    PasswordAgain password ->
      { model | passwordAgain = password }

    Age age ->
      { model | age = age }

-- VIEW


view : Model -> Html Msg
view model =
  div []
    [
      input [ type' "text", placeholder "Name", onInput Name ] [],
      input [ type' "password", placeholder "Password", onInput Password ] [],
      input [ type' "password", placeholder "Re-enter Password", onInput PasswordAgain ] [],
      input [ type' "age", placeholder "Age", onInput Age ] [],
      viewValidation model
    ]


viewValidation : Model -> Html Msg
viewValidation model =
  let
    (color, message) =
      if model.password /= model.passwordAgain then
        ("red", "passwords do not match")
      else if length model.password <= 8 then
        ("red", "Password must be at least 8 charaters")
      else
        ("green", "OK")
  in
    div [ style [("color", color)] ] [ text message ]
