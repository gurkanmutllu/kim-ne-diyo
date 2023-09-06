import { Navigate } from "react-router-dom";
import jwt_decode from "jwt-decode";
import {useAuth} from "../context/AuthContext.jsx";

export function PrivateRoute({ children, roles }) {
    // Rol bilgisini üst komponentten alıyoruz ve token rol bilgisiyle karşılaştırıyoruz
    const { tokenInfo,role } = useAuth();
    const userRole ="USER" // kullanıcı rolünü alıyoruz
    console.log("token : ", tokenInfo)
    if (!tokenInfo) {
        return <Navigate to="/login" replace />;
    } else if (roles && roles.indexOf(role) === -1) {
        return <Navigate to="/" replace />;
    }
    return children;
}