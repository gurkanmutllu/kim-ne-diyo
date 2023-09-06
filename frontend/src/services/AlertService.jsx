import Swal from "sweetalert2";

export const AlertService = {
    showWaiting: function () {
        Swal.fire({
            showConfirmButton: false,
            text: "Lütfen Bekleyiniz...",
        });
    },
    showOk: function () {
        Swal.fire({
            icon: "success",
            showConfirmButton: false,
            timer: 1500,
            text: "İşlem başarılı, Lütfen Bekleyiniz...",
        });
    },
    showError: function (errorModel) {
        console.error(errorModel);
        var errMsg = "";
        if (errorModel.resultCode ==="ValidationError") {
            errMsg = errorModel.err;
        } else if (errorModel.resultCode==="NotFound") {
            errorModel.errors.forEach((item) => {
                errMsg += `<li>${item.error}</li>`;
            });
            errMsg = `<ul>${errMsg}</ul>`;
        } else if (errorModel.resultCode==="Forbidden") {
            errMsg="Bu "
        } else if(errorModel.resultCode==="Conflict") {
            errMsg = "Çatışma hatası oluştu. Lütfen sonra tekrar deneyin veya destekle iletişime geçin. Anlayışınız için teşekkürler.";
        }
        Swal.fire({
            icon: "error",
            showConfirmButton: true,
            //   text: errMsg,
            html: errMsg,
            confirmButtonText: "Tamam",
        });
    },
    close: function () {
        Swal.close();
    },
};
