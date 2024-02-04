export interface RestWrapper<T> {
  respDate: string;
  responseCode: string;
  responseMessage: string;
  data: T;
}
