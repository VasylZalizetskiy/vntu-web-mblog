Java
----
TODO

1.1 Опис платформи

1.2 Організація коду: пакети та класи, методи, змінні й константи.

1.3 Збирач сміття

1.4 Наслідування та інтерфейси. Всі функції є віртуальними.

1.5 Основні типи

		boolean			        true | false
		byte		8 біт		-128 до 127
		char		16 біт		Від \u0000' \u0000' дo \uffff', або от 0 дo 65535
		short		16 біт		Від -32768 до 32767
		int			32 біта		Від -2147483648 до 2147483647
		long		64 біта		Від -9223372036854775808 до 9223372036854775807
		float		32 біта		Від 1,17549435e-38 дo 3,4028235e+38
		double		64 біта		Від 4,9e-324 дo 1,7976931348623157e+308

1.6 Модифікатори доступу

1.7 Приклад сервлету

1.8 Колекції: List та Map

1.9 Робота зі стрічками

1.10 Приклад JavaBean

Servlets
---------

Java Servlet API — API описаний в стандартах J2EE для створення динамічного контенту веб-додатків. Сервлети це аналог технологій PHP, CGI і ASP.NET для Java.

Інтерфейси та класи для написання сервлетів описані в пакетах javax.servlet і javax.servlet.http. Всі сервлети повинні реалізовувати інтерфейс Servlet, який визначає методи життєвого циклу сервлету. Але для реалізації обробки HTTP ми будемо наслідуватись від абстрактного класу HttpServlet, який забезпечує методи обробки HTTP запитів, такі як doGet і doPost.

    package ua.vntu.test;

    import java.io.*;
    import javax.servlet.*;
    import javax.servlet.annotation.*;
    import javax.servlet.http.*;

    @WebServlet("/hello")
    public class HelloWorld extends HttpServlet {

        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            PrintWriter out = response.getWriter();
            out.println("Hello World");
        }
    }

Отже основними методами, які необхідно перевизначити є:

`void doGet(HttpServletRequest req, HttpServletResponse resp)` - якщо сервлет підтримує HTTP `GET` запити.  
`void doPost(HttpServletRequest req, HttpServletResponse resp)` - для запитів HTTP `POST`  
`void doPut(HttpServletRequest req, HttpServletResponse resp)` - для HTTP `PUT` запитів  
`void doDelete(HttpServletRequest req, HttpServletResponse resp)` - для HTTP запитів `DELETE`  
`void init(ServletConfig config)` - викликається при старті - може використовуватись для налаштування сервлета та створення ресурсів.  
`void destroy()` - викликається при знищенні сервлета, може використовуватись для знищення ресурсів. 

В більшості сервлет контейнерів URL складається з адреси серверу, директорії додатку (оскільки їх може бути декілька на одному сервері) та власне частині URL, яка вказує який сервлет буде обробляти даний запит. 

Для вказання URL, який буде обробляти даний сервлет необхідно створювати файл дескриптор додатку - `web.xml` й там вказувати необхідні налаштування, однак починаючи з версії 3.0 це можна зробити простіше - лише вказавши анотацію `@WebServlet("/hello")`, яка означає, що сервлет прийматиме запити за URL `http://servername.com/app_name/hello`. 

Об’єкт `HttpServletRequest` має наступні методи, які ми будемо використовувати:

`String getContextPath()`
: повертає частину URI, яка визначає контекст запиту.

`String getHeader(String name)`
: повертає заголовок пакету з назвою name. Не чутливий до регістру.

`String getMethod()`
: повертає стрічку - метод запиту, один з `"GET"`, `"POST"`, `"PUT"`, `"DELETE"`, `"HEAD"`, `"TRACE"` або `"OPTION"`.

`String getParameter(String name)`
: повертає значення параметру скрипта з заданим ім’ям.

`String getPathInfo()`
: повертає частину URI без параметрів, яка слідує за шляхом сервлету.

`String getRemoteAddr()`
: поветає адресу з якої надходив запит.

Методи об’єкту `HttpServletResponse`:

`void addHeader(String name, String value)`
: додає у відповідь заголовок з іменем name і значенням value.

`void sendError(int sc, String msg)`
: відправляє у відповідь код HTTP помилки (наприклад 500 - внутрішня помилка сервера) та додає до відповіді повідомлення яке буде відображено в браузері користувача.

`void sendRedirect(String location)`
: відправляє на браузер відповідь, що необхідно запросити іншу сторінку з вказаним URI.

`PrintWriter getWriter()`
: отримати об’єкт типу PrintWriter для запису відповіді.

`void setCharacterEncoding(String charset)`
: встановити кодування відповіді.

`void setContentType(String type)`
: встановити MIME тип відповіді.

Cтруктура директорій веб додатку
------
Типова структура веб додатку:

    /src
    /WebContent
    +- WEB-INF
        +- web.xml
        +- lib
    +- index.jsp
    +- static/
        +- img/
        +- js/
        +- css/

2.2 веб архів TODO

2.3 розгортання на сервері TODO

2.4 Схема роботи сервера, проксі. TODO


JSP
--------

`JSP` (Java Server Pages) — технологія, що дозволяє веб-розробникам динамічно генерувати `HTML`, `XML` та інші веб-сторінки. JSP дозволяє додати динамічну складову прямо в HTML, добто JSP складається зі звичайного коду HTML, а динамічна частина коду додається за допомогою спеціальних тегів.

Рекомендоване розширення `JSP` файлу - `.jsp`. Сторінки може бути скомпонованою з декількох файлів-фрагментів й для таких фрагментів рекомендується використовувати розширення `.jspf`.

Роглянемо приклад `JSP` сторінки:

    <%@ page contentType="text/html; charset=UTF-8" %>
    <%@ page import="java.util.Date" %>
    <%-- комментар до сторінки --%>
    <html>
      <head><title>Localized Dates</title></head>
      <body>
         Час на сервері: <%=new Date()%>
      </body>
    </html>

Директива `<%@page contentType="..." %>` встановлює `Content-Type`, який буде повертати дана сторінка, для html підходить text/html, також додатково можна встановити кодування сторінки: `charset=UTF-8` 

Директива `<%@page import="..." %>` імпортує клас `Date` з пакету `java.util` - клас який ми використовуємо для видедення поточної дати та часу. Можливі атрибути директиви page:

	import="пакет.class"
	contentType="MIME-Type"
	isThreadSafe="true|false
	session="true|false"
	buffer="размерkb|none"
	autoflush="true|false"
	extends="пакет.class"
	info="сообщение"
	errorPage="url"
	isErrorPage="true|false"
	language="java"

Тег `<%= код %>` виконає код, розміщений в дужках і результат виконання виведе на місці тегу. Отже в наведеному прикладі буде створено новий об’єкт `Date` (який в конструкторі автоматично встановить дату та час на поточні) й виведено його у вихідний потік. При цьому об’єкт `Date` автоматично перетвориться до стрічки (фактично неявно буде викликано метод `toString`). Тобто приклад еквівалентний:

    out.println(new Date());

або

    out.println(new Date().toString());

Тег `<% %>` - називається скриплетом, дозволяє вміщувати java код в сторінку, наприклад:

    <% for(int i = 0; i < 100; i++) { %>
    	<p>Я завжди буду закривати парні теги.</p>
    <% } %>

Даний код виведе 100 параграфів тексту.

`JSP` сторінка скомпілюється в сервлет, в якому весь html код буде розміщено в методі doGet та обрамлено out.println(). Є можливість розмістити код в класі сервлету, поза методом doGet, для чого використовується тег <%! код %>.

`<jsp:include page="відносний URL" flush="true"/>` - вставляє вказаний файл (зазвичай jsp або html) на місці даного тегу.

`<jsp:forward page="відносний URL"/>` - передає запит вказаній сторінці

Для спрощення роботи в JSP є ряд наперед визначених змінних, які можна використовувати:
	
`HttpServletRequest request`
: об’єкт запиту

`HttpServletResponse response`
: об’єкт відповіді.

`HttpSession session`
: сессія користувача

`PrintWriter out`
: буферизований потік виведення, використовується для відправки даних клієнту. 

`ServletContext application`
: контекст додатку

`ServletConfig config`
: об’єкт з налаштуваннями сервлету.

Дані змінні можна вільно використовувати в скриплетах, наприклад:

    Ім’я вашого хоста: <%= request.getRemoteHost() %>

Або:

    <% 
        String queryData = request.getQueryString();
        out.println("Дані запиту: " + queryData); 
    %>

В JSP можна також використовувати вбудовану скриптову мову виразів (EL - Expression Language), яка дозволяє отримати доступ до Java компонентів з JSP.	Наприклад, щоб отримати доступ до об`єкта необхідно додати вираз `${name}`, для доступу до змінної цього об’єкту `${name.foo.bar}`.

В мові виразів можна скористатись одним з наперед визначених об’єктів:

`pageContext`
: Контекст сторінки, який надає доступ наступних змінних:

	`servletContext`
	: Контекст сервлету, з якого можна отримати налаштування сервлету
	
	`session`
	: сесія користувача
	
	`request`
	: Об’єкт запиту
	
	`response`
	: Об’єкт відповіді

Додатково є ряд об’єктів для швидкого доступу:

`param`
: надає доступ до параметрів запиту, наприклад `${param.username}` поверне значення параметру `username`.

`paramValues`
: аналогічний об’єкт, але повертає масив значень.

`header`
: надає доступ до заголовків

`headerValues`
: аналогічний об’єкт, але повертає масив значень для однієї назви заголовка.

`cookie`
: містить значення всіх cookies.

`initParam`
: параметри ініціалізації скрипта.

Останніми розглянемо об’єкти, які відносяться до різних областей видимості (scopes): 

`pageScope`
: містить змінні, які існують тільки для данної JSP сторінки.

`requestScope`
: містить змінні, які існують тільки під час обробки конктретного запиту, має більшу область видимості ніж pageScope, 
оскільки запит можуть обробляти декілька сторінок.

`sessionScope`
: містить змінні з сессії користувача.

`applicationScope`
: містить змінні прив’язані до додатку, існують весь час, поки працює додаток.

Приклад використання:

    <% pageContext.setAttribute("foo", "bar"); %>
 
 Далі можна доступатись до поля:

    From page scope: ${pageScope.foo} <br/>
    Or Simply: ${foo} <br/>

MVC
----
MVC (Model-View-Controller - Модель-Представлення-Контроллер) - підхід до проектування додатку, в якому відокремлюються 3 сутності дані програми - модель, код для відображення цих даних - представлення та контроллер - код, який відповідає за оновлення моделі відповідно до змін зроблених користувачем. Такий підхід дозволяє вільно модифікувати кожен з компонентів незалежно з мінімальним впливом на інші та значно спростити кожен з компонентів.

Наприклад, нам необхідно відобразити список користувачів системи. Тоді моделлю будуть наші дані, які нам необхідно відобразити тобто список користувачів об’єктів `User`. Представленням виступатиме JSP сторінка, яка буде цей список відображати користувачу. А, відповідно, контроллером - сервлети який отримуватиме список користувачів з бази даних чи іншого джерела та передавати його представленню. Отже, нехай об’єкти користувачів матимуть вигляд:

	public class User {
		private String login;
		private String email;

		public User(String login, String email) {
			this.login = login;
			this.email = email;
		}

		public String getLogin(){
		    return login; 
		}
		
		public String getEmail(){
		    return email; 
		}
	}

Список користувачів для простоти поки буде отримуватись зі звичайного списку. В реальній системі - список, швидше за все, буде отримуватись з бази. Зазвичай у веб додатках додатково виділяють шар доступу до бази даних (DAO - Data Access Object) та шар сервісів, тобто класів які безпосередньо містять саму логіку додатку. В нашому випадку шар DAO можна опустити, необхідним буде тільки сервіс: 

	public class UsersService {
		private List<User> users = new ArrayList<User>();

		public UsersService() {
			users.addAll(Arrays.asList(
				new User("test", "test@mail.com"),
				new User("admin", "admin@mail.com"),
				new User("user", "user@mail.com")
			));
		}

		public void register(User u) {
			users.add(u);
		}

		public List<User> getUsers() {
			return users;
		}
	}

Основна функція контроллера - отримати користувачів та передати їх на відображення:
    
    ...
    private UsersService srvc = new UsersService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("model", srvc.getUsers());

        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/view.jsp");
        view.forward(req, resp);
    }
    ...

Для передачі даних у JSP використали метод `req.setAttribute()` який дозволяє прив’язати будь-які дані до запиту (ці дані не передаються на сторону клієнту). Для передачі керування на JSP можна скористатись методом `forward` який передасть управління на JSP сторінку, при чому на відміну від `redirect` все відбуватиметься всередині серверу й користувачу повератиметься тільки дані сторінки. 

Код відображення даних - `view.jsp`:
    
	<%@ page import="java.util.List" %>
    <%
        List<User> users = (List<User>) request.getAttribute("model");
        for(User u: users) {
    %>
    	${u.login}
    <%    
        }
    %>
