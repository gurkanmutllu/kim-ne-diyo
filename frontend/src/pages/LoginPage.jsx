
import {Form, Formik} from "formik";
import Input from "../components/form/Input";
import {useAuth} from "../context/AuthContext";
import {useNavigate} from "react-router-dom";
import Swal from "sweetalert2";
import {Button, Col, Row} from 'antd';

export default function LoginPage() {
    const navigate = useNavigate();
    const {login,tokenInfo}=useAuth();
    const apiLoginUrl = import.meta.env.VITE_API_LOGIN_URL;
    const submitted = () => {
        Swal.fire(
            'İşlem Başarılı!',
            'Giriş Yapıldı',
            'success'
        )
    }
    const errorAlert = () => {
        Swal.fire({
                icon: "error",
                title: "Hata",
                text: "Kullanıcı adı ya da şifre hatalı"
            }
        )
    }
    const Home = () => {
        navigate("/")
    }
    const Login = (value) => {
        fetch(apiLoginUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(value),
        })
            .then((response) => response.json())  // Parse JSON response
            .then((data) => {
                console.log("value:", value)
                console.log("data:", data)
                if (data && data.data && data.data.token) {
                    //giriş işlemi başarılı
                    login(data); // result bilgisi fonksiyona gidiyor
                    submitted();
                    Home();
                } else {
                    errorAlert();
                }
            })
            .catch((error) => {
                // Handle error
                console.error(error);
            });
    };

    const getRegister = () => {
        navigate("/register")
    }

    return (
        <div style={{height: "calc(100vh - 16px)"}}>
            <Row className="rowStyleLogin" >
                <Col lg={12} md={0} sm={0} xs={0}>
                    <div className="gif" style={{display:"flex",justifyContent:"center"}}><img src={"src/assets/logo1.gif"}/></div>
                </Col>
                <Col lg={12} md={24} sm={24} xs={24}>
                    <div className="formStyleLogin">
                        <Formik
                            initialValues={{
                                name: "",
                                password: ""
                            }}
                            onSubmit={(value) => {
                                Login(value)
                            }}
                        >
                            {() => (
                                <div style={{marginTop:"5rem"}} >
                                    <h2 className="titleStyle">GİRİŞ EKRANI</h2>
                                    <Form>
                                        <Input type="text" label="Kullanıcı adınızı giriniz:" name="name"/>
                                        <Input type="text" label="Şifrenizi giriniz:" name="password"/>
                                        <div className={"actionButton"}>
                                            <Button htmlType="submit">Giriş Yap</Button>
                                        </div>
                                    </Form></div>
                            )}
                        </Formik>
                        <div className={"deger"}>Kullanıcı kaydınız yok mu?<Button type={"text"} onClick={getRegister}>Kayıt
                            Ol</Button></div>

                    </div>
                </Col>
            </Row>
        </div>
    );


}