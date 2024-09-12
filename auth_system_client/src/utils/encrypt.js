import CryptoJS from 'crypto-js'

const initKey = '7d7d7d7d7d7d7d7d7d7d7d7d7d7d7d7d'
// 获取填充后的key
const key = CryptoJS.enc.Hex.parse(initKey)

/**
 * AES加密算法
 * 加解密算法/工作模式/填充方式：AES/ECB/PKCS5Padding/128
 * 长度：128
 * 加密之后，转成16进制字符串传到后台
 */
export function aesEncrypt (data) {
  /**
   * CipherOption, 加密的一些选项:
   * mode: 加密模式, 可取值(CBC, CFB, CTR, CTRGladman, OFB, ECB), 都在 CryptoJS.mode 对象下
   * padding: 填充方式, 可取值(Pkcs7, AnsiX923, Iso10126, Iso97971, ZeroPadding, NoPadding), 都在 CryptoJS.pad 对象下
   * iv: 偏移量, mode === ECB 时, 不需要 iv
   * 返回的是一个加密对象
   */
  const cipher = CryptoJS.AES.encrypt(data, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7
  })
  // 将加密后的数据转换成 Base64
  const baseHex = cipher.ciphertext.toString()
  // 返回加密后的经过处理的 Base64
  return baseHex
}

export function aesDecrypt (data) {
  const encryptedHexStr = CryptoJS.enc.Hex.parse(data)
  const srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr)
  const decrypt = CryptoJS.AES.decrypt(srcs, key, { iv: '', mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 })
  const decryptedStr = decrypt.toString(CryptoJS.enc.Utf8)
  return decryptedStr.toString()
}
