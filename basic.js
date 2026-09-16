function lookAndSay(str) {
  let result = "";

  for (let i = 0; i < str.length; i++) {
    let count = 1;

    while (i + 1 < str.length && str[i] === str[i + 1]) {
      count++;
      i++;
    }

    result += count + str[i];
  }

  return result;
}
