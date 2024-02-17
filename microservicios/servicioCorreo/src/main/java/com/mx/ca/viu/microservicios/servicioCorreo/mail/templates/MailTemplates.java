/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.servicioCorreo.mail.templates;

/**
 *
 * @author rzavaleta
 */
public final class MailTemplates {
    
    public static final String RECUPERA_PASS_TEMPLATE = "<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <title>Dore jQuery</title>\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n" +
        "\n" +
        "</head>\n" +
        "\n" +
        "<body style=\"margin:0; padding:0; background-color:#f8f8f8; padding-top: 10px;\">\n" +
        "    <!--Mailing Start-->\n" +
        "    <div leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\" style=\"height:auto !important;width:100% !important; font-family: Helvetica,Arial,sans-serif !important; margin-bottom: 40px;\">\n" +
        "        <center>\n" +
        "            <table bgcolor=\"#ffffff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px; background-color:#ffffff;border:1px solid #e4e2e2;border-collapse:separate !important; border-radius:4px;border-spacing:0;color:#242128; margin:0;padding:40px;\"\n" +
        "                heigth=\"auto\">\n" +
        "                <tbody>\n" +
        "                    <tr>\n" +
        "                        <td align=\"center\" valign=\"center\" style=\"padding-bottom:40px;border-top:0;height:100% !important;width:100% !important;\">\n" +
        "                            <img src=\"https://amextrafinanzas.com/images/logossite/cont_LogosFinanzas/Logo_x1_100.png\" />\n" +
        "                        </td>                        \n" +
        "                    </tr>\n" +
        "                    <tr>\n" +
        "                        <td colspan=\"2\" style=\"padding-top:10px;border-top:1px solid #e4e2e2\">\n" +
        "                            <h3 style=\"color:#518F44; font-size:18px; line-height: 1.6; font-weight:500;\">Recuperación de contraseña</h3>\n" +
        "                            <p style=\"color:#8f8f8f; font-size: 14px; padding-bottom: 20px; line-height: 1.4;\">\n" +
        "                                ¡Hola %_NOMBRE_USUARIO_%!,\n" +
        "                            </p>\n" +
        "                            <p style=\"color:#8f8f8f; font-size: 14px; padding-bottom: 20px; line-height: 1.4;\">\n" +
        "                                Hemos recibido una solicitud para restablecer la contraseña de tu cuenta en nuestra plataforma. Si no has realizado esta solicitud, puedes ignorar este mensaje.\n" +
        "                            </p>\n" +
        "                            <p style=\"color:#8f8f8f; font-size: 14px; padding-bottom: 20px; line-height: 1.4;\">\n" +
        "                                Para continuar con el proceso de recuperación de contraseña, por favor utiliza el siguiente código de verificación:\n" +
        "                            </p>\n" +
        "                            <h3 style=\"color:#518F44; font-size:18px; line-height: 1.6; font-weight:500;\">Código de verificación</h3>\n" +
        "                            <p style=\"background-color:#f1f1f1; padding: 8px 15px; border-radius: 50px; display: inline-block; margin-bottom:20px; font-size: 14px;  line-height: 1.4; font-family: Courier New, Courier, monospace; margin-top:0; letter-spacing: 3PX;\">%_CODIGO_RECUPERA_PASS_%</p>\n" +
        "\n" +
        "                            <p style=\"color:#8f8f8f; font-size: 14px; padding-bottom: 20px; line-height: 1.4;\">\n" +
        "                                Una vez que hayas ingresado el código de verificación, podrás restablecer tu contraseña y acceder nuevamente a tu cuenta. Por razones de seguridad, te recomendamos que el nuevo contraseña sea única y no la compartas con nadie.                                \n" +
        "                            </p>\n" +
        "\n" +
        "                            <p style=\"color:#8f8f8f; font-size: 14px; padding-bottom: 20px; line-height: 1.4;\">\n" +
        "                                Si necesitas asistencia adicional o tienes alguna pregunta, no dudes en ponerte en contacto con nuestro equipo de soporte.                                \n" +
        "                            </p>\n" +
        "\n" +
        "                            <p style=\"color:#8f8f8f; font-size: 14px; padding-bottom: 20px; line-height: 1.4;\">\n" +
        "                                Atentamente,\n" +
        "                            </p>\n" +
        "\n" +
        "                            <p style=\"color:#8f8f8f; font-size: 14px; padding-bottom: 20px; line-height: 1.4;\">\n" +
        "                                El eqipo de <strong style=\"color: #518F44;\">AMEXTRA FINANZAS</strong>                                \n" +
        "                            </p>\n" +
        "\n" +
        "                        </td>\n" +
        "                    </tr>                    \n" +
        "                </tbody>\n" +
        "            </table>\n" +
        "            <table style=\"margin-top:30px; padding-bottom:20px;; margin-bottom: 40px;\">\n" +
        "                <tbody>\n" +
        "                    <tr>\n" +
        "                        <td align=\"center\" valign=\"center\">\n" +
        "                            <p style=\"font-size: 12px; text-decoration: none;line-height: 1; color:#909090; margin-top:0px; \">\n" +
        "                                AMEXTRA, Todos los Derechos Reservaod\n" +
        "                            </p>                            \n" +
        "                        </td>\n" +
        "                    </tr>\n" +
        "                </tbody>\n" +
        "            </table>\n" +
        "        </center>\n" +
        "    </div>\n" +
        "    <!--Mailing End-->\n" +
        "</body>\n" +
        "\n" +
        "</html>";
    
}
