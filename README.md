<!DOCTYPE html>
<html lang="es">
<body>
    <h1>Sistema de Gestión Bancaria</h1>  
    <p>
        Este proyecto es una aplicación web desarrollada como parte del Trabajo Práctico Integrador para la materia <strong>Laboratorio IV</strong> de la <strong>Tecnicatura Universitaria en Programación</strong> en la <strong>Universidad Tecnológica Nacional</strong>. Su objetivo principal es la gestión de un sistema bancario que incluye usuarios administradores y clientes, cumpliendo con los requisitos funcionales y técnicos estipulados.
    </p>
    <h2>Tecnologías Utilizadas</h2>
    <ul>
        <li><strong>Java</strong>: Lenguaje principal para la implementación del backend.</li>
        <li><strong>MySQL</strong>: Base de datos relacional para almacenar la información.</li>
        <li><strong>Servlets y JSP</strong>: Tecnologías Java para la construcción de la capa web.</li>
        <li><strong>Bootstrap</strong>: Framework para el diseño responsivo y estética del frontend.</li>
        <li><strong>HTML, CSS, JavaScript</strong>: Tecnologías básicas para la interfaz de usuario.</li>
    </ul>
    <h2>Características</h2>
    <h3>Funcionalidades del Administrador</h3>
    <ol>
        <li><strong>Gestión de Clientes</strong>:
            <ul>
                <li>Alta, baja, modificación y listado (ABML).</li>
                <li>Creación automática de usuario y contraseña al dar de alta un cliente.</li>
                <li>Eliminación lógica de usuarios al eliminar un cliente.</li>
            </ul>
        </li>
        <li><strong>Gestión de Cuentas</strong>:
            <ul>
                <li>Alta, baja, modificación y asignación de cuentas a clientes.</li>
                <li>Límite de 3 cuentas activas por cliente.</li>
                <li>Monto inicial de $10,000 para nuevas cuentas.</li>
            </ul>
        </li>
        <li><strong>Préstamos</strong>:
            <ul>
                <li>Aprobación o rechazo de préstamos solicitados por clientes.</li>
                <li>Generación automática de cuotas para el pago de préstamos.</li>
            </ul>
        </li>
        <li><strong>Reportes y Estadísticas</strong>:
            <ul>
                <li>Información procesada basada en los datos del sistema.</li>
                <li>Filtros por fechas y otros parámetros relevantes.</li>
            </ul>
        </li>
    </ol>
    <h3>Funcionalidades del Cliente</h3>
    <ol>
        <li><strong>Consulta de Información</strong>:
            <ul>
                <li>Visualización de datos personales.</li>
                <li>Historial de movimientos de cuentas.</li>
            </ul>
        </li>
        <li><strong>Transferencias</strong>:
            <ul>
                <li>Entre cuentas propias.</li>
                <li>Hacia cuentas de otros clientes usando el CBU.</li>
            </ul>
        </li>
        <li><strong>Gestión de Préstamos</strong>:
            <ul>
                <li>Solicitud de préstamos indicando monto y cantidad de cuotas.</li>
                <li>Selección de cuenta para la acreditación.</li>
                <li>Pago de cuotas con selección de cuenta origen.</li>
            </ul>
        </li>
        <li><strong>Restricciones</strong>:
            <ul>
                <li>Transferencias solo permitidas si hay saldo suficiente.</li>
                <li>No se permite saldo negativo en las cuentas.</li>
            </ul>
        </li>
    </ol>
    <h3>Aspectos Técnicos</h3>
    <ul>
        <li><strong>Arquitectura en tres capas</strong>:
            <ul>
                <li>División del código en capas: DAO, Negocio y Controlador (Servlets).</li>
                <li>Validaciones y manejo de excepciones centralizado.</li>
            </ul>
        </li>
        <li><strong>Manejo de Excepciones</strong>:
            <ul>
                <li>Uso de excepciones personalizadas.</li>
            </ul>
        </li>
        <li><strong>Validaciones</strong>:
            <ul>
                <li>Formularios validados para evitar campos vacíos o datos inválidos.</li>
            </ul>
        </li>
        <li><strong>Paginación y Búsquedas</strong>:
            <ul>
                <li>Listados con paginación y filtros por diferentes criterios.</li>
            </ul>
        </li>
        <li><strong>Mensajes de Confirmación</strong>:
            <ul>
                <li>Ejemplo: "¿Está seguro que desea eliminar el registro?"</li>
            </ul>
        </li>
    </ul>
    <h3>Imagenes</h3>
  
   ![{1E468DD7-B9D6-4709-9EFC-6C41FF050346}](https://github.com/user-attachments/assets/850ec1d6-c18d-455c-8a1b-f2386a023285)

  ![{84F47608-37B4-4346-931C-220CDB36347F}](https://github.com/user-attachments/assets/e69a1e9a-c9a3-43e4-9f77-afffadbfa5dc)
  
  ![{0B822411-35E8-41ED-9B61-AD43072B2751}](https://github.com/user-attachments/assets/b274d08f-9a38-45f6-819f-69b3d5fb73d5)


</body>
</html>
