module.exports = {
  extends: [
    "airbnb",
    "airbnb/hooks"
  ],
  env: {
    browser: true,
    jest: true
  },
  rules: {
    "arrow-parens": [
      "warn",
      "as-needed"
    ],
    "comma-dangle": [
      "warn",
      "only-multiline"
    ],
    camelcase: [
      "warn",
      {
        properties: "always"
      }
    ],
    curly: "warn",
    indent: [
      "warn",
      2
    ],
    "jsx-quotes": [
      "warn",
      "prefer-double"
    ],
    "max-len": [
      "warn",
      {
        code: 120,
        ignoreComments: true,
        ignoreUrls: true,
        ignoreStrings: true,
        tabWidth: 2
      }
    ],
    "no-duplicate-imports": "warn",
    "no-mixed-spaces-and-tabs": [
      "warn",
      "smart-tabs"
    ],
    "no-trailing-spaces": "warn",
    "object-curly-spacing": [
      "warn",
      "always"
    ],
    "padded-blocks": [
      "warn",
      {
        blocks: "never",
        classes: "never"
      },
      {
        allowSingleLineBlocks: false
      }
    ],
    "padding-line-between-statements": [
      "warn",
      {
        blankLine: "any",
        prev: "class",
        next: "*"
      },
      {
        blankLine: "always",
        prev: "*",
        next: "return"
      }
    ],
    "quote-props": [
      "warn",
      "as-needed"
    ],
    quotes: [
      "warn",
      "double",
      {
        allowTemplateLiterals: true
      }
    ],
    "react/jsx-closing-bracket-location": [
      "warn",
      "tag-aligned"
    ],
    "react/jsx-wrap-multilines": [
      "warn",
      {
        declaration: "parens-new-line",
        assignment: "parens-new-line",
        return: "parens-new-line",
        arrow: "parens-new-line",
        condition: "ignore",
        logical: "ignore",
        prop: "ignore"
      }
    ],
    semi: [
      "warn",
      "always"
    ],
    "space-before-blocks": "warn",
    "space-before-function-paren": "warn",
    "space-infix-ops": "warn"
  }
};
