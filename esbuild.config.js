const esbuild = require('esbuild-wasm');

esbuild.build({
    entryPoints: ['src/main/js/app.js'],
    bundle: true,
    treeShaking: false,
    format: "iife",
    platform: "browser",
    globalName: "SeizureTracker",
    outfile: "build/resources/main/static/bundle.js"
}).catch(() => process.exit(1));
