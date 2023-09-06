import {ResultCode} from "./ResultCode";

export interface ResponseDTO {
    resultCode: ResultCode;
    message: string | null;
    data: any;
}