import {Layout, Menu, theme, Carousel, Card, Button} from 'antd';

const {Meta} = Card;
import {useEffect, useState} from 'react';
import Avatar from '@mui/material/Avatar';
import {deepOrange, deepPurple} from '@mui/material/colors';
import Stack from '@mui/material/Stack';
import {AppstoreOutlined, HomeOutlined, UserOutlined} from "@ant-design/icons";
import {useAuth} from "../context/AuthContext.jsx";
import {useNavigate} from 'react-router-dom';
import SubMenu from "antd/es/menu/SubMenu.js";

const {Header, Content, Footer} = Layout;
const HomePage = () => {
    const {tokenInfo, getUserId, logOut} = useAuth();
    const [data, setData] = useState([]);
    const [isLoading, setIsloading] = useState(false);
    const [newsList, setNewsList] = useState([])
    const newGetUrl = import.meta.env.VITE_API_GETNEWS_URL
    const navigate = useNavigate();
    const getLogOut = () => {
        logOut()
        navigate('/login');
    };

    // const getInitials = (name) => {
    //     const names = name.split(' ');
    //     const initials = names.map(n => n[0]).join('');
    //     return initials.toUpperCase();
    // };
    const getNews = async () => {
        try {
            const response = await fetch(newGetUrl, {
                method: "GET",
                headers: {
                    'Authorization': `Bearer ${tokenInfo}`,
                },
            });

            if (!response.ok) {
                throw new Error("Veri alınamadı");
            }
            const responseData = await response.json();
            const dataa = responseData.data
            // console.log("newss:",dataa)
            setNewsList(dataa);
        } catch (error) {
            console.error("Hata oluştu:", error);
        }
    };
    useEffect(() => {
        getNews();
    }, []);
    const {
        token: {colorBgContainer},
    } = theme.useToken();
    return (
        <Layout className="layout">
            <Header  className="header">
                <div className="logo" />
                <Menu theme="dark" mode="horizontal"  defaultSelectedKeys={['1']} style={{ lineHeight: '64px' }}>
                    <Menu.Item key="1" icon={<HomeOutlined />}>
                        Home
                    </Menu.Item>
                    <Menu.Item key="2" icon={<UserOutlined />}>
                        Profile
                    </Menu.Item>
                    <Menu.Item key="3" icon={<AppstoreOutlined />}>
                        Products
                    </Menu.Item>
                    <Menu.Item key="4" icon={<AppstoreOutlined />}>
                        About
                    </Menu.Item>
                    <SubMenu key="sub" title={<Avatar style={{ backgroundColor: '#722ed1' }}>BT</Avatar>} style={{ float: 'right' }}>
                        {/* Avatar veya kullanıcı adı gibi öğeler burada ekleyebilirsiniz */}
                        <Menu.Item key="5">
                            <Button onClick={getLogOut}>Çıkış Yap</Button>
                        </Menu.Item>
                    </SubMenu>
                </Menu>
            </Header>
            <Content
                style={{
                    padding: '0 50px',
                }}
            >
                <div
                    className="site-layout-content"
                    style={{
                        background: colorBgContainer,
                    }}
                >
                    <div className="carouselStyle">
                        {/*{console.log("token info bilgisi: ", tokenInfo)}*/}
                        <Carousel autoplay slidesToShow={4}>
                                {newsList.map(news => (

                                    <Card
                                        key={news.id}
                                        hoverable
                                        style={{
                                            width: '100%', // Bütün kartlar aynı genişlikte olacak
                                            boxShadow: '0px 2px 6px rgba(0, 0, 0, 0.1)', // Gölgelendirme
                                            marginBottom: '16px', // Kartlar arasına boşluk eklemek için
                                        }}
                                        cover={<img src={`data:image/png;base64, ${news.imgFile}`}   style={{ height: '160px', objectFit: 'cover' }} />}
                                    >
                                        <div style={{ padding: '12px' }}>
                                            <h3 style={{ marginBottom: '8px', fontSize: '18px',textAlign:"center" }}>{news.header}</h3>
                                            <p style={{ fontSize: '14px', color: '#888',textAlign:"center" }}>{news.content}</p>
                                        </div>
                                    </Card>
                                )
                                    )}

                        </Carousel>

                    </div>
                </div>
            </Content>
            <Footer
                style={{
                    textAlign: 'center',
                }}
            >
                Tüm hakları BNT 'de saklıdır.
            </Footer>
        </Layout>
    );
};
export default HomePage;


