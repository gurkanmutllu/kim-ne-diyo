import {Navigate, Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage"
import RegisterPage from "../pages/RegisterPage";
import LoginPage from "../pages/LoginPage";
import FetchPage from "../components/Fetch";
import EmptyPage from "../pages/EmptyPage";
import NewsDetails from "../pages/NewsDetails.jsx"
import {PrivateRoute} from "./PrivateRoute.jsx";
import {useAuth} from "../context/AuthContext.jsx";
import {roles} from "./roles.jsx";
export default  function AppRouteList() {
    const {tokenInfo}=useAuth();
  return (
    <>
    <Routes>
        {/*<Route path="/" element={<PrivateRoute><HomePage/></PrivateRoute>}/>*/}
        {/*{!tokenInfo&&<Route path="/register" element={<RegisterPage/>}/>}*/}
        {/*{!tokenInfo&&<Route path="/login" element={<LoginPage/>}/>}*/}
       <Route path="/" element={<HomePage/>}/>
        <Route path="/login" element={<LoginPage/>}/>
        <Route path="/register" element={<RegisterPage/>}/>
        <Route path="/fetch" element={<PrivateRoute roles={[roles.admin,roles.user]}><FetchPage/></PrivateRoute>}/>
        <Route path="/empty" element={<EmptyPage/>}/>
        <Route path="/newsdetail" element={<PrivateRoute roles={[roles.user]}><NewsDetails/></PrivateRoute>}/>
        <Route path={"*"} element={<Navigate to={"/"}/> }/>
    </Routes>
    </>
  )
}


