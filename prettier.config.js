/** @type {import("prettier").Config} */
const config = {
    trailingComma: "es5",
    tabWidth: 4,
    semi: false,
    singleQuote: true,
    endOfLine: 'lf',
    bracketSameLine: true,
    printWidth: 120,
    plugins: [
        "prettier-plugin-tailwindcss",
        "prettier-plugin-java"
    ]
};

module.exports = config;