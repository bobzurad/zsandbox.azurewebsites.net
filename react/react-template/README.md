A react app made from scratch.

Configured to use:

* Webpack
* SASS
* Image File Loading (png, svg, jpg, gif)
* Jest
* ESLint

To use this template to start a new project, simply copy this template, or follow the steps below if you want to start from scratch.

### How to create a react project from scratch (and how this template was created):

1. Create the package.json file

```bash
yarn init
```

2. Install Babel and React

```bash
yarn add @babel/core @babel/preset-react @babel/preset-env react react-dom
```

3. Add Babel config (`.babelrc`)

```javascript
{
  "presets": [
    "@babel/preset-react",
    "@babel/preset-env"
  ]
}
```

4. Install Webpack and CSS loaders

```bash
yarn add webpack webpack-cli babel-loader webpack-dev-server html-webpack-plugin css-loader style-loader
```

5. Add Webpack config (`webpack.config.js`)

```javascript
const HtmlWebpackPlugin = require("html-webpack-plugin");
const path = require("path");

module.exports = {
  entry: "src/index.jsx",
  output: {
    filename: "bundle.[hash].js",
    path: path.resolve(__dirname, "dist")
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: "public/index.html"
    })
  ],
  resolve: {
    modules: [__dirname, "src", "node_modules"],
    extensions: ["*", ".js", ".jsx"],
  },
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        loader: require.resolve("babel-loader")
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"]
      },
      {
        test: /\.s?css$/,
        use: ["style-loader", "css-loader", "sass-loader"]
      },
      {
        test: /\.(png|svg|jpg|gif)$/,
        use: [
          "file-loader"
        ]
      }
    ]
  }
};
```

6. Add `public/index.html`

```html
<!doctype html>
<html>

<head>
  <meta charset="utf-8" />
  <title>React Template</title>
</head>

<body>
  <div id="root"></div>
  <script src="/build/bundle.js"></script>
</body>

</html>
```

7. Add `src/App.jsx`

```javascript
import React from "react";
import "./App.scss";

const App = () => (
  <div className="container">
    <h1>Hello World, React!</h1>
  </div>
);

export default App;
```

8. Add `src/App.css`

```css
.container {
    align-items: center;
    display: flex;
    justify-content: center;
    padding-top: 10px;
    width: 100%;
}
```

9. Add `src/index.jsx`

```javascript
import React from 'react'
import ReactDOM from 'react-dom'

import App from './App'

ReactDOM.render(<App />, document.getElementById("root"))
```

10. Update `package.json` to add scripts for building and starting the app.

```json
"scripts": {
    "start": "webpack-dev-server --hot --open",
    "build": "webpack --config webpack.config.js --mode production"
  }
```

You can now build the app with `yarn build` and run the app with `yarn start`

### Add a linter

Follow these steps to add ESLint with airbnb config.

1. Install ESLint

```bash
yarn add --dev eslint eslint-config-airbnb eslint-plugin-react eslint-plugin-react-hooks eslint-plugin-jsx-a11y eslint-plugin-import
```

2. Add `.eslintrc.js`

```json
module.exports = {
  extends: [
    "airbnb",
    "airbnb/hooks"
  ],
  env: {
    browser: true
  },
};
```

3. Add `.eslintignore`

```txt
/dist/
```

4. Add scripts to `package.json`

```json
    "lint": "eslint **/*.{js,jsx}",
    "lint-fix": "eslint --fix **/*.{js,jsx}",
```

If you're using VS Code, install the ESLint extension `dbaeumer.vscode-eslint`, to automatically format your code files as you edit them.

### Add SASS support and image file loader

1. Install packages

```bash
yarn add --dev sass-loader node-sass file-loader
```

2. Update `webpack.config.js`

```javascript
      {
        test: /\.s?css$/,
        use: ["style-loader", "css-loader", "sass-loader"]
      },
      {
        test: /\.(png|svg|jpg|gif)$/,
        use: [
          "file-loader"
        ]
      }
```

3. Replace `src/App.css` with `src/App.scss`

```scss
$default-background: #D3D3D3;

body {
    height: 100%;
    background-color: $default-background;
}

.container {
    align-items: center;
    display: flex;
    justify-content: center;
    padding-top: 10px;
    width: 100%;
}
```

### Add Jest for Unit Testing

1. Install Jest and Enzyme

```bash
yarn add --dev jest babel-jest identity-obj-proxy
yarn add enzyme enzyme-adapter-react-16
```

2. Add `jest.config.json`

```json
{
  "roots": ["<rootDir>/src"],
  "setupFilesAfterEnv": ["<rootDir>/setupEnzyme.js"],
  "moduleFileExtensions": ["js", "jsx", "json", "node"],
  "moduleNameMapper": {
    "^.+\\.(css|less|scss)$": "babel-jest",
    "^.+\\.(jpg|png|gif|svg)$": "identity-obj-proxy"
  }
}

```

3. Add `setupEnzyme.js`

```javascript
const enzyme = require("enzyme");
const Adapter = require("enzyme-adapter-react-16");

enzyme.configure({ adapter: new Adapter() });
```

4. Update `.eslintrc.js`

```javascript
  env: {
    browser: true,
    jest: true
  },
```

5. Add a test (`src/App.test.jsx`)

```javascript
import React from "react";
import { shallow } from "enzyme";
import App from "./App";

describe("App", () => {
  it("renders without crashing", () => {
    const wrapper = shallow(<App />);

    expect(wrapper).toBeTruthy();
  });
});
```

6. Add scripts to `packages.json`

```json
    "test": "jest"
```

You can now run unit tests by running `yarn test`

