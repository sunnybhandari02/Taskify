Taskify
A full-stack task management application designed to enhance productivity and simplify task tracking with a seamless user experience. Built with ReactJS, Java, Spring Boot, JPA, MySQL, and deployed on AWS with custom domain integration.





🚀 Live Demo
🔗 taskify.blog

📌 Features
User Authentication: Secure login and registration using Spring Security with JWT.

Task Management: Create, read, update, and delete (CRUD) tasks.

Responsive UI: Built with ReactJS for a modern and intuitive user experience.

Backend API: RESTful APIs implemented using Spring Boot and JPA for database operations.

Database: MySQL hosted on AWS EC2 for cost-effective infrastructure.

Cloud Deployment:

Backend deployed on AWS EC2.

Frontend hosted on AWS S3 with CloudFront and mapped with a custom domain via Route 53.

Secure access with HTTPS enabled.

🛠️ Tech Stack
Category	Technology
Frontend	ReactJS, HTML, CSS, JavaScript
Backend	Java, Spring Boot, Spring Security, JPA
Database	MySQL
Deployment	AWS EC2, AWS S3, CloudFront, Route 53
Authentication	JWT
Version Control	Git, GitHub

⚙️ Installation and Setup
1️⃣ Clone the Repository
bash
Copy
Edit
git clone https://github.com/sunnybhandari02/Taskify.git
cd Taskify
2️⃣ Backend Setup (Spring Boot)
bash
Copy
Edit
cd backend
# Configure application.properties with your database credentials
./mvnw clean install
java -jar target/todoWebApp-0.0.1-SNAPSHOT.jar
3️⃣ Frontend Setup (React)
bash
Copy
Edit
cd frontend
npm install
npm start
🌐 Deployment
Backend: Deployed on AWS EC2 instance with MySQL database installed on the same server.

Frontend: Deployed on AWS S3, configured with CloudFront and Route 53 for custom domain and HTTPS support.

📸 Screenshots
Login Page	Dashboard

📈 Key Highlights
End-to-end cloud deployment ensuring 99.9% uptime.

Secure authentication using JWT.

Scalable and production-ready architecture.

🤝 Contributing
Contributions are welcome!

Fork the project

Create your feature branch (git checkout -b feature/AmazingFeature)

Commit your changes (git commit -m 'Add some AmazingFeature')

Push to the branch (git push origin feature/AmazingFeature)

Open a Pull Request

📜 License
This project is licensed under the MIT License.
