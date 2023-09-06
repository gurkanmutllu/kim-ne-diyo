import { useContext, useEffect } from "react";
import { useState } from "react";
import { createContext } from "react";
import {useNavigate} from "react-router-dom";
import jwt_decode from "jwt-decode";

const authContext = createContext();
const Provider = ({ children }) => {
  const navigate = useNavigate();
  const getLogin = () => {
    navigate("/login")
  }
  const [tokenInfo, setTokenInfo] = useState(JSON.parse(localStorage.getItem("tokenInfo")));
  const [userInfo, setUserInfo] = useState(null);
  const [role,setRole]=useState("USER");

  useEffect(() => {
    const storedToken = JSON.parse(localStorage.getItem("tokenInfo"));
console.log("storedToken: ", storedToken)
    if (storedToken) {
      setTokenInfo(storedToken);
    } else {
      getLogin()
    }
  }, []);
  const login = (result) => {
    localStorage.setItem("tokenInfo", JSON.stringify(result?.data?.token));
    setTokenInfo(result?.data?.token);
    const role = jwt_decode(result?.data?.token).role;
    setRole(role);
  };
  const logOut = () => {
    localStorage.removeItem("tokenInfo");
    setTokenInfo(null);
    setRole(null);
    console.log(tokenInfo)
  };


  const getUserId =()=>{
    if(tokenInfo){
      var decoded = jwt_decode(tokenInfo);
      return decoded.userId;
    }else {
      return null;
    }
  }
  const getUserInfo = () => {
    if (tokenInfo) {
      var decoded = jwt_decode(tokenInfo);
      return decoded.name
    } else {
      return null;
    }
  };


  const data = { tokenInfo, userInfo, login,getUserId,role,logOut ,getUserInfo};
  return <authContext.Provider value={data}>{children}</authContext.Provider>;
};

export const useAuth = () => useContext(authContext);
export default Provider;
