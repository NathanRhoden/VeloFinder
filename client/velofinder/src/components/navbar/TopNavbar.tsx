import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";

export default function TopNavBar() {
  return (
    <div>
      <Navbar bg="light" expand="lg">
        <Container>
          <Navbar.Brand href="/">VELOFINDER</Navbar.Brand>
          <Nav>
            <Nav.Link href="login">Login</Nav.Link>
            <Nav.Link href="ridercreate">Register</Nav.Link>
            <Nav.Link href="user">Profile</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </div>
  );
}
