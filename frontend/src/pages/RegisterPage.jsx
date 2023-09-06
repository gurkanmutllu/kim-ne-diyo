import {Form, Formik} from "formik";
import Input from "../components/form/Input";
import Swal from "sweetalert2";
import {Col, Row, Button} from 'antd';
import {useNavigate} from "react-router-dom";
import {AlertService} from "../services/AlertService.jsx";
import {ResultCode} from "../dtos/ResultCode.ts";

export default function RegisterPage() {
    const navigate = useNavigate();
    const apiUrl = import.meta.env.VITE_API_BASE_URL;
    const errorAlert = () => {
        Swal.fire({
            position: "center",
            icon: "error",
            title: "Kayıt başarısız",
            showConfirmButton: false,
            timer: 1700
        })
    }
    const submitted = () => {
        Swal.fire(
            "Kayıt Başarılı!",
            "Kullanıcı başarılı bir şekilde kaydedildi.",
            "success"
        )
    }
    const getLogin = () => {
        navigate("/login")
    }
    const handleFormSubmit = async (value, resetForm) => {
        try {
            const response = await fetch(apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(value),
            }).then((res) => {
                console.log("res:", res)
                if (res) {
                    res.json().then((data) => {
                        const deger = data.message
                        const matchAlert = () => {
                            Swal.fire({
                                position: "center",
                                icon: 'error',
                                title: deger,
                                showConfirmButton: false,
                                timer: 1700,
                                html: "Hata aldınız!!"
                            })
                        }
                        if (data.password !== data.confirmPassword) {
                            matchAlert()
                            return;//return döndürdüğünde password eşleşmeyince kayıt işlemine geçilmemesini sağlıyoruz

                        }
                        console.log("gelen data:", data)
                        switch (data.resultCode) {
                            case ResultCode.Success:
                                AlertService.showOk();
                                break;
                            case ResultCode.ValidationError:
                                console.log("Kayıt başarısız")
                                matchAlert()
                                break;
                            case ResultCode.NotFound:
                                matchAlert()
                                break;
                            case ResultCode.Forbidden:
                                matchAlert()

                                break;
                            case ResultCode.Conflict:
                                matchAlert()
                                throw new Error('Registration failed');
                            default:
                                // Handle other cases
                                break;
                        }
                    })
                }

            });
            resetForm();
        } catch (error) {
            // Handle error
            console.error(error);
        }
    };
    return (<div className={"registerPage"}>
            <Row className="rowStyle">
                <Col className={"gif"} md={0} sm={0} xs={0} lg={12}>
                    <img src="src/assets/logo1.gif"/></Col>
                <Col md={24} lg={12} xs={24} sm={24}>
                    <div className="formStyle" style={{minHeight: "30rem"}}
                    >
                        <Formik
                            initialValues={{
                                name: "",
                                userName: "",
                                userSurname: "",
                                userMail: "",
                                userPhone: "",
                                password: "",
                                confirmPassword: "",
                            }}
                            onSubmit={(value, {resetForm}) => {
                                handleFormSubmit(value, resetForm)

                            }}
                        >
                            {() => (<div className={"registerScroll"}>
                                    <h2 className="titleStyle">KAYIT EKRANI</h2>
                                    <Form>
                                        <Input type="text" label="İsim giriniz:" name="userName"/>
                                        <Input type="text" label="Soyisim giriniz:" name="userSurname"/>
                                        <Input type="text" label="Kullanıcı adı giriniz:" name="name"/>
                                        <Input type="text" label="Mail adresinizi giriniz:" name="userMail"/>
                                        <Input type="text" label="Telefon numaranızı giriniz:" name="userPhone"/>
                                        <Input style={{textAlign: "inherit"}} type="text" label="Şifrenizi giriniz:"
                                               name="password"/>
                                        <Input type="text" label="Şifreyi tekrar girin:" name={"confirmPassword"}/>
                                        <div className={"actionButton"}>
                                            <button type="submit">KAYDOL</button>
                                        </div>
                                    </Form>
                                </div>
                            )}
                        </Formik>
                        <div className="deger">
                            <div>Daha önce kayıt yaptırdınız mı?</div>
                            <Button type="text" onClick={getLogin}>Giriş Yap</Button></div>
                    </div>
                </Col>
            </Row></div>

    );


}
//
// import { Form, Formik } from "formik";
// import Input from "../components/form/Input";
// import Swal from "sweetalert2";
// import { Col, Row,Button } from 'antd';
// import { useNavigate } from "react-router-dom";
//
// export default function RegisterPage  ()  {
//
//     const navigate = useNavigate();
//
//     const submitted=()=>{
//         Swal.fire(
//             "Kayıt Başarılı!",
//             "Kullanıcı başarılı bir şekilde kaydedildi." ,
//             "success"
//         )
//     }
//     const getLogin=()=>{
//         navigate("/login")
//     }
//
//     const handleFormSubmit = async (value,resetForm) => {
//         if (value.password !== value.confirmPassword) {
//             alert('Passwords do not match!');
//         }
//
//         await  fetch('http://localhost:8080/api/register', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//             body: JSON.stringify(value),
//         })
//             .then((response) => {
//                 console.log("response:",response)
//                 if (response.ok) {
//                     // Registration successful
//                     response.json().then((data) => {
//                         if (data.resutCode === "Success") {
//                             console.log('Registration successful');
//                         } else {
//                             console.log("Gelen Mesaj:", data);
//                             throw new Error(data.message);
//                         }
//                     });
//                     //submitted()
//                 } else {
//                     // Handle registration error
//                     throw new Error('Registration failed');
//                 }
//             })
//             .catch((error) => {
//                 // Handle error
//                 console.error(error);
//             });
//         resetForm();
//         // Reset the form after successful registration
//
//     };
//
//     return (
//         <Row className="rowStyle">
//             <Col span={12}>{/*<div><h1>KİM NE DİYO</h1></div> */}
//                 <img src="src/assets/logo1.gif"/></Col>
//             <Col span={12}><div className="formStyle">
//                 <Formik
//                     initialValues={{
//                         name:"",
//                         userName:"",
//                         userSurname:"",
//                         userMail:"",
//                         userPhone:"",
//                         password:"",
//                         confirmPassword:"",
//                     }}
//                     onSubmit={(value,{ resetForm }) => {
//                         handleFormSubmit(value, resetForm )
//
//                     }}
//                 >
//                     {() => (<div >
//                             <h2 className="titleStyle">KAYIT EKRANI</h2>
//                             <Form >
//                                 <Input  type="text" label="Kullanıcı adı giriniz:" name="name" />
//                                 <Input type="text" label="İsminizi giriniz:" name="userName" />
//                                 <Input type="text" label="Soyisminizi giriniz:" name="userSurname" />
//                                 <Input type="text" label="Mail adresinizi giriniz:" name="userMail" />
//                                 <Input type="text" label="Telefon numaranızı giriniz:" name="userPhone" />
//                                 <Input type="text" label="Şifrenizi giriniz:" name="password" />
//                                 <Input type="text" label="Şifreyi tekrar girin:" name={"confirmPassword"}/>
//                                 <button type="submit">KAYDOL</button>
//                             </Form>
//                         </div>
//                     )}
//                 </Formik>
//                 <div className="buttonStyle"><div>Daha önce kayıt yaptırdınız mı?</div><Button type="text" onClick={getLogin}>Giriş Yap</Button></div>
//
//
//             </div></Col>
//         </Row>
//
//     );
//
//
// }

