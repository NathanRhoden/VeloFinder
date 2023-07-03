import LoginFormComponent from "../components/forms/loginform/LoginFormComponent"
import LoginError from "../types/LoginError"

export default function LoginForm(failure: LoginError) {

    console.log( failure)

    return (
        <div>
            <LoginFormComponent error={failure.error} />
        </div>
    )
}