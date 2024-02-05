export class Utils {
  static getSessionStorageByKey<T>(key: string): T {
    return JSON.parse(sessionStorage.getItem(key));
  }
}
