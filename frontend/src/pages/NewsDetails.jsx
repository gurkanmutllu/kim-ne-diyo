import React, {useState} from 'react';
import {Form, Formik} from 'formik';
import Input from '../components/form/Input';
import File from '../components/form/File';
import {Col, Row, Button} from 'antd';
import {useNavigate} from 'react-router-dom';
import {useAuth} from "../context/AuthContext.jsx";

export default function NewsDetails() {
    const {tokenInfo, getUserId} = useAuth()
    const [imgFile, setImgFile] = useState()
    const newsUrl = import.meta.env.VITE_API_NEWS_URL;
    const navigate = useNavigate();
    const getLogin = () => {
        navigate('/login');
    };
    const handleImgChange = (event) => {
        const file = event.target.files[0];
        setImgFile(file);
        console.log(file)
        // Display a preview of the selected image
        const reader = new FileReader();
        reader.onload = (e) => {
        };
        reader.readAsDataURL(file);
        return file
    };
    const handleFormNews = async (values, resetForm) => {
        console.log("Test")
        console.log(values)
        try {
            const formData = new FormData();
            formData.append("header", values.header)
            formData.append("content", values.content)
            formData.append("imgFile", values.imgFile);
            formData.append("userId", getUserId())
            const response = await fetch(newsUrl, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${tokenInfo}`,
                },
                body: formData
            });
            if (response.ok) {
                const data = await response.json();
                console.log('response:', response);
                console.log('data:', data);
                console.log("file:",data.data.imgFile)
                alert('Haber kaydedildi');
            } else {
                throw new Error('Network response was not ok');
            }

        } catch (error) {
            console.error('hata aldınız: ', error);
        }
        resetForm();
    };

    return (
        <div className={'registerPage'}>
            <Row className="rowStyle">
                <Col className={'gif'} md={0} sm={0} xs={0} lg={12}>
                    <img src="src/assets/logo1.gif" alt="logo"/>
                </Col>
                <Col md={24} lg={12} xs={24} sm={24}>
                    <div
                        className="formStyle"
                        style={{minHeight: '30rem'}}
                    >
                        <Formik
                            initialValues={{
                                header: '',
                                content: '',
                                imgFile: null,
                                userId: getUserId()
                            }}
                            onSubmit={(values, {resetForm}) => {
                                handleFormNews(values, resetForm)
                                console.log("degerler:",values)
                            }}
                        >
                            {() => (
                                <div className={'registerScroll'}>
                                    <h2 className="titleStyle">
                                        HABER EKLEME EKRANI
                                    </h2>
                                    <Form>
                                        <Input
                                            type="text"
                                            label="Haber başlığını ekleyiniz:"
                                            name="header"
                                        />
                                        <Input
                                            type="text"
                                            label="Haberi ekleyiniz:"
                                            name="content"
                                        />

                                        <File
                                            name="imgFile"
                                            label="Dosya"

                                        />

                                        <div className={'actionButton'}>
                                            <Button
                                                style={{
                                                    fontSize: '20px',
                                                    display: 'flex',
                                                    justifyContent: 'center',
                                                    alignItems: 'center',
                                                }}
                                                htmlType={"submit"}
                                            >
                                                Haberi Oluştur
                                            </Button>
                                        </div>
                                    </Form>
                                </div>
                            )}
                        </Formik>
                    </div>
                </Col>
            </Row>
        </div>
    );
}
