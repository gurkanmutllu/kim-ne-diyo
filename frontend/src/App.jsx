
import AuthProvider from "./context/AuthContext"
import AppRouteList from "./routes/AppRouteList"
import './components/form/form.css'
function App() {

  return (
    <AuthProvider>
      <AppRouteList/>
    </AuthProvider>
  )
}

export default App
