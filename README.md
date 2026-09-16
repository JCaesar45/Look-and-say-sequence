# Aurum Sequence

## Product intent
A luxury, high-conversion single-file web page for the Look-and-Say sequence.
The page is designed as a premium brand surface: high-contrast gold typography,
restrained motion, structured product data, and immediate interactive proof.

## Methodological choices
- The Look-and-Say transform is implemented as a linear run-length encoding pass:
  O(n) time, O(n) output size.
- The front end is a single HTML document with embedded CSS and JavaScript so the
  artifact runs without a build step or network dependency.
- JSON-LD embeds a schema.org Product and SoftwareApplication graph for discoverability.
- Backend blueprints are included as Python FastAPI, TypeScript Fastify, and Java
  Spring Boot implementations of the same endpoint contract.
- Local storage persists sequence state and leads in the browser, making the demo
  fully operative without a server.

## API contract
POST /api/look-and-say
Request: {"value":"1211"}
Response: {"input":"1211","output":"111221"}

## Verification
lookAndSay("1") -> "11"
lookAndSay("11") -> "21"
lookAndSay("21") -> "1211"
lookAndSay("1211") -> "111221"
lookAndSay("3542") -> "13151412"

## References
Ecma International. (n.d.). ECMAScript language specification. Retrieved September 16, 2026, from https://tc39.es/ecma262/
Mozilla Developer Network. (n.d.). JavaScript. Retrieved September 16, 2026, from https://developer.mozilla.org/en-US/docs/Web/JavaScript
Mozilla Developer Network. (n.d.). CSS. Retrieved September 16, 2026, from https://developer.mozilla.org/en-US/docs/Web/CSS
WHATWG. (n.d.). HTML Living Standard. Retrieved September 16, 2026, from https://html.spec.whatwg.org/
Schema.org. (n.d.). Product. Retrieved September 16, 2026, from https://schema.org/Product
Python Software Foundation. (n.d.). Python 3 documentation. Retrieved September 16, 2026, from https://docs.python.org/3/
Microsoft. (n.d.). TypeScript documentation. Retrieved September 16, 2026, from https://www.typescriptlang.org/docs/
Oracle. (n.d.). Java documentation. Retrieved September 16, 2026, from https://docs.oracle.com/en/java/
W3C. (n.d.). Web Content Accessibility Guidelines (WCAG) 2.2. Retrieved September 16, 2026, from https://www.w3.org/TR/WCAG22/
