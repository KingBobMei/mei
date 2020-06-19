import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

export function getToken() {
  console.log("到底装的什么token" + TokenKey)
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  console.log("装的什么鬼" + token)
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
