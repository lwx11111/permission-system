import md5 from 'js-md5'
import sha256 from 'sha256';
import {aesEncrypt} from './encrypt';

export function getEncryptPassword(password, type) {

  if (type === 'md5') {
    return md5(password);
  } else if (type === 'sha256') {
    return sha256(password);
  } else if (type === 'aes') {
    return aesEncrypt(password);
  } else {
    return password;
  }
}
