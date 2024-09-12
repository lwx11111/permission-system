const alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'

export default {
  /**
   * 生成随机的guid
   * @param {*生成随机id的长度} length ，默认18
   */
  createGuid (length = 18) {
    let ret = ''
    let i = 0
    const l = alphabet.length
    while (i++ < length) {
      let index = Math.round(Math.random() * l)
      if (index < 0) index = 0
      if (index >= l) index = l - 1
      ret += alphabet[index]
    }
    return ret
  },

  guid () {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
      const r = Math.random() * 16 | 0; const v = c === 'x' ? r : (r & 0x3 | 0x8)
      return v.toString(16)
    })
  },
  addGuid (model, length = 18) {
    if (!model.GUID) {
      let ret = ''
      let i = 0
      const l = alphabet.length
      while (i++ < length) {
        let index = Math.round(Math.random() * l)
        if (index < 0) index = 0
        if (index >= l) index = l - 1
        ret += alphabet[index]
      }
      model.GUID = ret
    }
    return model.GUID
  },
  isJSON (str) {
    try {
      JSON.parse(str)
    } catch (e) {
      return false
    }
    return true
  }
}
